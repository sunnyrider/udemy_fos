package com.food.ordering.system.application.handler;

import java.util.Objects;

import jakarta.annotation.Generated;

public class ErrorDTO {

	private final String code;
	private final String message;

	@Generated("SparkTools")
	private ErrorDTO(Builder builder) {
		this.code = builder.code;
		this.message = builder.message;
	}

	public ErrorDTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ErrorDTO [code=" + code + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorDTO other = (ErrorDTO) obj;
		return Objects.equals(code, other.code) && Objects.equals(message, other.message);
	}

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String code;
		private String message;

		private Builder() {
		}

		public Builder Code(String code) {
			this.code = code;
			return this;
		}

		public Builder Message(String message) {
			this.message = message;
			return this;
		}

		public ErrorDTO build() {
			return new ErrorDTO(this);
		}
	}
}
