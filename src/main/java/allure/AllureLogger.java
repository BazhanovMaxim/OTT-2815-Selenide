package allure;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;


/**
 * Оболочка логгера
 * <p>
 * Created by a.epishin on 13.04.2018
 */
public class AllureLogger {

    private static final Logger logger = LogManager.getLogger(AllureLogger.class);

    @Step("{0}")
    public static void info(final String message) {
        logger.info("[INFO] ".concat(message));
    }

    @Step("{0}")
    public static void fail(final String message) {
        attachScreenshot();
    }

    @Step("{0}")
    public static void fail(final String message, Throwable throwable) {
        attachScreenshot();
    }

    @Step("{0}")
    public static void error(final String message) {
        attachScreenshot();
        logger.error("[ERROR] ".concat(message));
    }

    /**
     * проверяет равенство значений и выдает сообщение об успехе
     * вида comment: успешно выполнена проверка значения 'checkValue.toString()'
     * либо об ошибке
     * вида comment: значение 'checkValue.toString()' не равно 'baseValue.toString()'
     *
     * @param checkedValueName комментарий к сравниваемым параметрам
     * @param checkedValue     полученное значение, которое сравнивается со вторым
     * @param expectedValue    ожидаемое значение
     */
    @Step("{0}")
    public static void equals(String checkedValueName, Object checkedValue, Object expectedValue) {
        if ((expectedValue instanceof Boolean) && (String.valueOf(checkedValue).length() > 0)) {
            info(String.format("Параметр '%s': значение '%s' соответствует непустому ожидаемому", checkedValueName, String.valueOf(checkedValue)));
            return;
        }
        if (Objects.equals(checkedValue, expectedValue))
            info(String.format("Параметр '%s': значение '%s' соответствует ожидаемому", checkedValueName, String.valueOf(checkedValue)));
        else
            error(String.format("Параметр '%s': значение '%s' не равно ожидаемому '%s'"
                    , checkedValueName, String.valueOf(checkedValue), String.valueOf(expectedValue)));
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screen = robot.createScreenCapture(screenRect);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(screen, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Attachment(value = "Вложение", type = "application/json", fileExtension = ".json")
    public static byte[] getBytesAnnotationWithArgs(String resourceName){
        try {
            return Files.readAllBytes(Paths.get("target\\TestsFiles\\", resourceName));
        } catch (IOException e) {
            return null;
        }
    }
}
