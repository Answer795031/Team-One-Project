package pro.sky.teamoneproject.utils;

public class LocationUtils {
    /**
     * @param latitude - Широта маркера
     * @param longitude - Долгота маркера
     * @return ссылка на яндекс.карты с установленным маркером
     */
    public static String getYandexUrl(float latitude, float longitude) {
        return getYandexUrl(latitude, longitude, latitude, longitude, 16f);
    }

    /**
     * @param mapLatitude - Широта карты
     * @param mapLongitude - Долгота карты
     * @param pointLatitude - Широта маркера
     * @param pointLongitude - Долгота маркера
     * @param zoom - уровень масштабирования
     * @return ссылка на яндекс.карты с установленным маркером
     */
    public static String getYandexUrl(float mapLatitude, float mapLongitude, float pointLatitude, float pointLongitude, float zoom) {
//        return String.format("https://yandex.ru/maps/?ll=%s,%s&z=%s&pt=%s,%s&mode=search&text=%s,%s", mapLongitude, mapLatitude, zoom, pointLongitude, pointLatitude, pointLongitude, pointLatitude);
        return String.format("https://yandex.ru/maps/?ll=%s,%s&mode=routes&pt=%s,%s&rtext=~%s,%s&rtt=auto&z=%s", mapLongitude, mapLatitude, mapLongitude, mapLatitude, mapLatitude, mapLongitude, zoom);
    }

    /**
     * @param latitude - Широта маркера
     * @param longitude - Долгота маркера
     * @return ссылка на 2gis с установленным маркером
     */
    public static String get2Gis(float latitude, float longitude) {
        return get2Gis(latitude, longitude, latitude, longitude, 16f);
    }

    /**
     * @param mapLatitude - Широта карты
     * @param mapLongitude - Долгота карты
     * @param pointLatitude - Широта маркера
     * @param pointLongitude - Долгота маркера
     * @param zoom - уровень масштабирования
     * @return ссылка на 2gis с установленным маркером
     */
    public static String get2Gis(float mapLatitude, float mapLongitude, float pointLatitude, float pointLongitude, float zoom) {
        return String.format("https://2gis.ru/geo/%s,%s?m=%s,%s%s", mapLongitude, mapLatitude, pointLongitude, pointLatitude, "%2F" + zoom);
    }
}
