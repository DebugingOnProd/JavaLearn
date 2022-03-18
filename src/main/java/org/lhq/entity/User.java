package org.lhq.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @program: JavaLearn
 * @description:
 * @author: wang.defa
 * @create: 2021-11-04 19:24
 */

@Data
@Accessors(chain = true)
public class User {

    /**
     * 主键
     */
    private Long id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
	private String nickname;

	/**
     * 个人简介
     */
    private String introduction;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 个人资料背景图
     */
    private String background;

    /**
     * 账号创建日期时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;

    private String name;
    private String mobile;
    private LocalDateTime createTime;

    private Boolean status;
    /**
     * 逻辑删除，1可用，0不可用
     */
    private Boolean state;
}
