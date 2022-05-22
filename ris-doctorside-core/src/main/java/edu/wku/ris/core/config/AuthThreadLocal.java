package edu.wku.ris.core.config;

import edu.wku.ris.core.pojo.bo.DoctorLogInBO;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/25 20:20
 */
public class AuthThreadLocal {

    private static final ThreadLocal<DoctorLogInBO> doctorAuthThreadLocal = new ThreadLocal<>();

    public static DoctorLogInBO getCurrentDoctor(){
        return doctorAuthThreadLocal.get();
    }

    public static void setDoctor(DoctorLogInBO doctor){
        doctorAuthThreadLocal.set(doctor);
    }

    public static void clean(){
        if (doctorAuthThreadLocal.get() != null){
            doctorAuthThreadLocal.remove();
        }
    }
}
