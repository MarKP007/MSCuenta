package com.ejercicio.MSCuenta.utils;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

// TODO: Auto-generated Javadoc
/**
 * The Class EjercicioUtil.
 */
public final class EjercicioUtil {

	/**
	 * Es campo lleno.
	 *
	 * @param campo the campo
	 * @return true, if successful
	 */
	public static boolean esCampoLleno(String campo) {
		if (null == campo || campo.isEmpty() || campo.isBlank()) {
			return false;
		}
		return true;
	}

	/**
	 * Es campo lleno.
	 *
	 * @param campo the campo
	 * @return true, if successful
	 */
	public static boolean esCampoLleno(Date campo) {
		if (null == campo) {
			return false;
		}
		return true;
	}

	/**
	 * Es campo lleno.
	 *
	 * @param campo the campo
	 * @return true, if successful
	 */
	public static boolean esCampoLleno(int campo) {
		if (0 == campo) {
			return false;
		}
		return true;
	}

	/**
	 * Es campo lleno.
	 *
	 * @param campo the campo
	 * @return true, if successful
	 */
	public static boolean esCampoLleno(double campo) {
		if (0 == campo) {
			return false;
		}
		return true;
	}

	/**
	 * Convert list.
	 *
	 * @param <T>  the generic type
	 * @param <U>  the generic type
	 * @param from the from
	 * @param func the func
	 * @return the list
	 */
	public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
		return from.stream().map(func).collect(Collectors.toList());
	}

}
