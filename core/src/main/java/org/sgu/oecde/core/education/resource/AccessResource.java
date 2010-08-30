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

    private static final long serialVersionUID = 141L;

    /**
     * {@inheritDoc }
     */
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
}
