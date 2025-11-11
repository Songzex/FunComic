package com.scy.chat.message;

import lombok.Data;


@Data
public class ConnectMessage extends WebsocketMessage{

	/**
     * 登录token
	 */
	private String token;

}
