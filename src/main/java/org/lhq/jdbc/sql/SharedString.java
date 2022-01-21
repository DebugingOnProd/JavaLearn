package org.lhq.jdbc.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.lhq.utils.StringPool;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SharedString implements Serializable {
	private static final long serialVersionUID = -1536422416594422874L;

	/**
	 * 共享的 string 值
	 */
	private String stringValue;

	/**
	 * SharedString 里是 ""
	 */
	public static SharedString emptyString() {
		return new SharedString(StringPool.EMPTY);
	}

	/**
	 * 置 empty
	 *
	 * @since 3.3.1
	 */
	public void toEmpty() {
		stringValue = StringPool.EMPTY;
	}

	/**
	 * 置 null
	 *
	 * @since 3.3.1
	 */
	public void toNull() {
		stringValue = null;
	}
}
