package org.lhq.entity;

import lombok.Data;

/**
 * @author Wallace
 */
@Data
public class DbConfig {
    private String url;
    private String username;
    private String password;
    private String driver;
    private String encoding;
    private int threadNumber;
    private int connections;
}
