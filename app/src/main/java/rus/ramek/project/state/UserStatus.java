package rus.ramek.project.state;

/**
 * Created by KeeMekZaMak on 10/9/2016.
 */

public enum UserStatus {
    NORMAL,WAITING_APPROVE,DISABLED;

    public static UserStatus getStatus(String status){
        if("S0001".equalsIgnoreCase(status))
            return NORMAL;
        if("S0000".equalsIgnoreCase(status))
            return WAITING_APPROVE;
        if("S0002".equalsIgnoreCase(status))
            return DISABLED;

        return NORMAL;
    }
}
