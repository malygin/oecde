package org.sgu.oecde.de.users;

import org.sgu.oecde.core.users.AbstractTeacher;

/**
 * преподаватель дистанционного образования
 * @author shihovmy
 */
public class Teacher extends AbstractTeacher{

    private static final long serialVersionUID = 52L;

    public Teacher() {
    }

    public Teacher(Long id) {
        setId(id);
    }
}
