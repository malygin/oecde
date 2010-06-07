package org.sgu.oecde.core;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.type.NullableType;
import org.hibernate.type.TypeFactory;
import org.hibernate.usertype.EnhancedUserType;
import org.hibernate.usertype.ParameterizedType;
/**
 * TODO заюзать fast method and class
 * mash up of org.hibernate.EnumType (hibernate-anntotation) and <a href="http://community.jboss.org/wiki/Java5EnumUserType">that</a> example
 * @author ShihovMY
 */
public class GenericEnumUserType implements EnhancedUserType, ParameterizedType, Serializable {
    private static final String DEFAULT_IDENTIFIER_METHOD_NAME = "name";
    private static final String DEFAULT_VALUE_OF_METHOD_NAME = "valueOf";
    private static final long serialVersionUID = 66L;

    private Class<? extends Enum> enumClass;
    private Class<?> identifierType;
    private Method identifierMethod;
    private Method valueOfMethod;
    private NullableType type;
    private int[] sqlTypes;

    public void setParameterValues(Properties parameters) {
        String enumClassName = parameters.getProperty("enumClass");
        try {
            enumClass = Class.forName(enumClassName).asSubclass(Enum.class);
        } catch (ClassNotFoundException cfne) {
            throw new HibernateException("Enum class not found", cfne);
        }

        String identifierMethodName = parameters.getProperty("identifierMethod", DEFAULT_IDENTIFIER_METHOD_NAME);

        try {
            identifierMethod = enumClass.getMethod(identifierMethodName, new Class[0]);
            identifierType = identifierMethod.getReturnType();
        } catch (Exception e) {
            throw new HibernateException("Failed to obtain identifier method", e);
        }
        type = (NullableType)  TypeFactory.basic(identifierType.getName());

        if (type == null)
            throw new HibernateException("Unsupported identifier type " + identifierType.getName());

        sqlTypes = new int[] { type.sqlType() };
        String valueOfMethodName = parameters.getProperty("valueOfMethod", DEFAULT_VALUE_OF_METHOD_NAME);

        try {
            valueOfMethod = enumClass.getMethod(valueOfMethodName, new Class[] { identifierType });
        } catch (Exception e) {
            throw new HibernateException("Failed to obtain valueOf method", e);
        }
    }

    public Class returnedClass() {
        return enumClass;
    }

    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        Object identifier = type.get(rs, names[0]);
        if (rs.wasNull()) {
            return null;
        }

        try {
            return valueOfMethod.invoke(enumClass,  identifier );
        } catch (Exception e) {
            throw new HibernateException("Exception while invoking valueOf method '" + valueOfMethod.getName() + "' of " +
                    "enumeration class '" + enumClass + "'", e);
        }
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        try {
            if (value == null) {
                st.setNull(index, type.sqlType());
            } else {
                Object identifier = identifierMethod.invoke(value, new Object[0]);
                type.set(st, identifier, index);
            }
        } catch (Exception e) {
            throw new HibernateException("Exception while invoking identifierMethod '" + identifierMethod.getName() + "' of " +
                    "enumeration class '" + enumClass + "'", e);
        }
    }

    public int[] sqlTypes() {
        return sqlTypes;
    }

    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    public boolean isMutable() {
        return false;
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public String objectToSQLString(Object value) {
		boolean isOrdinal = isOrdinal( sqlTypes );
		if ( isOrdinal ) {
			int ordinal = ( (Enum) value ).ordinal();
			return Integer.toString( ordinal );
		}
		else {
			return '\'' + ( (Enum) value ).name() + '\'';
		}
    }

    @Override
    public String toXMLString(Object value) {
       boolean isOrdinal = isOrdinal( sqlTypes );
		if ( isOrdinal ) {
			int ordinal = ( (Enum) value ).ordinal();
			return Integer.toString( ordinal );
		}
		else {
			return ( (Enum) value ).name();
		}
    }

    @Override
    public Object fromXMLString(String xmlValue) {

			return enumClass;
    }

    private boolean isOrdinal(int[] paramType) {
            boolean ordinal = true;
            for(int i:paramType){
		switch ( i ) {
			case Types.INTEGER:
			case Types.NUMERIC:
			case Types.SMALLINT:
			case Types.TINYINT:
			case Types.BIGINT:
			case Types.DECIMAL: //for Oracle Driver
			case Types.DOUBLE:  //for Oracle Driver
			case Types.FLOAT:   //for Oracle Driver
				ordinal&=true;
			case Types.CHAR:
			case Types.LONGVARCHAR:
			case Types.VARCHAR:
				ordinal=false;
			default:
				throw new HibernateException( "Unable to persist an Enum in a column of SQL Type: " + paramType );
		}
            }
            return ordinal;
	}
}
