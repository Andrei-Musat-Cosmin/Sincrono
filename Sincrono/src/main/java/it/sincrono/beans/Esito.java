package it.sincrono.beans;

import java.util.Arrays;
import java.util.Objects;

public class Esito {

	private Integer code = 0;
	private String target = null;
	private String[] args = null;

	public Esito(Integer code, String target) {
		this.code = code;
		this.target = target;
	}

	public Esito() {
		super();
		// Auto-generated constructor stub
	}

	public Esito(Integer code, String target, String[] args) {
		super();
		this.code = code;
		this.target = target;
		this.args = args;
	}

	@Override
	public String toString() {
		return "Esito [code=" + code + ", target=" + target + ", args=" + Arrays.toString(args) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(args);
		result = prime * result + Objects.hash(code, target);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Esito other = (Esito) obj;
		return Arrays.equals(args, other.args) && Objects.equals(code, other.code)
				&& Objects.equals(target, other.target);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

}