package com.KeyStone.FieldService2.Security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.KeyStone.FieldService2.Enum.Permissions;
import com.KeyStone.FieldService2.Enum.Role;

public class RoleBasedPermissions {

    private static final Map<Role, Set<Permissions>> permissions = new HashMap<>();

    static {

        // MANAGER
        permissions.put(Role.MANAGER, new HashSet<>(Arrays.asList(
                Permissions.CREATE_USER,
                Permissions.UPDATE_USER,
                Permissions.VIEW_USER,
                Permissions.DELETE_USER,

                Permissions.CREATE_CUSTOMER,
                Permissions.UPDATE_CUSTOMER,
                Permissions.VIEW_CUSTOMER,
                Permissions.DELETE_CUSTOMER,

                Permissions.CREATE_SITE,
                Permissions.UPDATE_SITE,
                Permissions.VIEW_SITE,
                Permissions.DELETE_SLTE,

                Permissions.CREATE_WO,
                Permissions.UPDATE_WO,
                Permissions.VIEW_WO,
                Permissions.ASSIGN_WO,
                Permissions.CANCEL_WO,
                Permissions.CLOSE_WO,

                Permissions.ADD_PARTS,
                Permissions.UPDATE_PARTS,
                Permissions.VIEW_PARTS,
                Permissions.USE_PARTS,

                Permissions.ADD_TIME_LOGS,
                Permissions.VIEW_TIME_LOGS,

                Permissions.VIEW_DASHBOARD,
                Permissions.VIEW_REPORT,

                Permissions.SEND_NOTIFICATIONS
        )));


        // DISPATCHER
        permissions.put(Role.DISPATCHER, new HashSet<>(Arrays.asList(
                Permissions.CREATE_CUSTOMER,
                Permissions.UPDATE_CUSTOMER,
                Permissions.VIEW_CUSTOMER,

                Permissions.CREATE_SITE,
                Permissions.UPDATE_SITE,
                Permissions.VIEW_SITE,

                Permissions.CREATE_WO,
                Permissions.UPDATE_WO,
                Permissions.VIEW_WO,
                Permissions.ASSIGN_WO,
                Permissions.CANCEL_WO,

                Permissions.VIEW_DASHBOARD
        )));


        // TECHNICIAN
        permissions.put(Role.TEHNICIAN, new HashSet<>(Arrays.asList(
                Permissions.VIEW_WO,

                Permissions.START_WORK,
                Permissions.HOLD_WRK,
                Permissions.RESUME_WORK,
                Permissions.COMPLETE_WORK,

                Permissions.ADD_PARTS,
                Permissions.USE_PARTS,
                Permissions.VIEW_PARTS,

                Permissions.ADD_TIME_LOGS,
                Permissions.VIEW_TIME_LOGS
        )));


        // CUSTOMER
        permissions.put(Role.CUSTOMER, new HashSet<>(Arrays.asList(
                Permissions.RAISE_REQUEST,
                Permissions.VIEW_OWN_REQUEST_STATUS
        )));
    }


    // Getter method
    public static Map<Role, Set<Permissions>> getRoleBasedPermission() {
        return permissions;
    }
}