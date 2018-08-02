package com.abubusoft.kripton.basicsample.model;

import java.util.Date;

public interface Comment {
    long getId();
    long getProductId();
    String getText();
    Date getPostedAt();
}