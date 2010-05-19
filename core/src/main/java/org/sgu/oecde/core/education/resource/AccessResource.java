package org.sgu.oecde.core.education.resource;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 18.05.2010
 * Виды доступа у ресурса
 */
public enum AccessResource {

    //доступен вообще все пользователем (даже анонимным)
    publicAll,
    //доступен пользователям в рамках университета
    publicUniversity,
    //доступен сотрудникам департамента пользователя
    publicDepartament,
    //доступен только самому пользователю - по умолчанию
    notPublic;

    @Override
    public String toString() {
        switch(this){
            case publicAll:
                return "ресурс, доступный всем";
            case publicUniversity:
                return "ресурс, доступный в рамках университета";
            case publicDepartament:
                return "ресурс, доступный департаменту автора";
            case notPublic:
                return "закрытый ресурс";
            default:
                return "";
        }
    }
        public String toName(){
        switch(this){
            case publicAll:
                return "4";
            case publicUniversity:
                return "3";
            case publicDepartament:
                return "2";
            case notPublic:
                return "1";
            default:
                return "1";
        }
    }

    public static AccessResource parse(String name){
        if("4".equals(name))
            return publicAll;
        else if("3".equals(name))
            return publicUniversity;
        else if("2".equals(name))
            return publicDepartament;
        else if("1".equals(name))
            return notPublic;
        else
            return notPublic;
    }
    
}
