package io.github.shniu.arts.design.oo;

import java.awt.*;

/**
 * 抽象的例子
 */
public interface IPictureStore {
    void savePicture(Picture picture);
    Image getPicture(String pictureId);
    void deletePicture(String pictureId);
    void modifyMetaInfo(String pictureId, PictureMetaInfo metaInfo);
}
