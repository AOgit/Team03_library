package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public abstract class StringHashing {

    public static String hashPassword(String password) {
        try {
            // Получаем экземпляр MessageDigest для алгоритма SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Хэшируем пароль
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Преобразуем хэш в строку в формате Base64 для удобства хранения
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка хэширования пароля", e);
        }
    }

//    public static void main(String[] args) {
////        String  password = "superAdmin";
////        String password = "admin";
////        String password = "user";
////        String password = "user1";
//        String password = "reader";
//
//
//        String hashedPassword = hashPassword(password);
//        System.out.printf("Хэш пароля - %s - %s\n", password, hashedPassword);
//
//    }

}
