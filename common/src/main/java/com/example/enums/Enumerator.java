package com.example.enums;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 自定义枚举接口
 *
 * @author jinghong
 */
@JsonSerialize(using = EnumSerializer.class)
public interface Enumerator extends IEnum<Integer> {

	/**
	 * 描述
	 * @return
	 */
	String getDesc();

	/**
	 * 值
	 * @return
	 */
	@Override
	@JsonValue
    Integer getValue();

	/**
	 * 获取枚举值
	 * @param enumClazz 枚举类型
	 * @param value 值
	 * @return
	 */
	static <E extends Enum & Enumerator> E getEnumByValue(final Class<E> enumClazz, final Object value) {
		final E quietly = (E) EnumUtil.fromStringQuietly(enumClazz, Objects.toString(value));
		if (quietly != null) {
			return quietly;
		}

		final Optional<E> any = Stream.of(enumClazz.getEnumConstants())
				.filter(enumerator -> Objects.equals(Convert.toStr(enumerator.getValue()), value)).findAny();

		if (any.isPresent()) {
			return any.get();
		}

		throw new IllegalArgumentException("No element matches " + value);
	}

}
