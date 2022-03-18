package org.lhq.entity;

import lombok.Data;
import org.lhq.entity.enums.MessageType;

@Data
public class Message {
	private Integer id;
	private Integer fromId;
	private Integer destId;
	private MessageType messageType;
	private String message;
}
