package com.coinomi.stratumj.messages;

import com.google.common.base.Optional;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Giannis Dzegoutanis
 */
public class CallMessage extends BaseMessage {

	public CallMessage(String source) throws JSONException {
		super(source);
	}

	public CallMessage(String method, List params) {
		super();
		setMethod(method);
		setParams(params);
	}

	public CallMessage(String method, String param) {
		super();
		setMethod(method);
		setParams(Arrays.asList(param));
	}

	public CallMessage(long id, String method, String param) {
		this(method, param);
		setId(id);
	}

	public CallMessage(long id, String method, List params) {
		this(method, params);
		setId(id);
	}

	private void createParamsIfNeeded() {
		if (!has("params")) {
			try {
				put("params", new JSONArray());
			} catch (JSONException e) {
				// Should never happen because "params" is a valid JSON name
				throw new RuntimeException(e);
			}
		}
	}

	public String getMethod() {
		return optString("method", "");
	}

	public void setMethod(String method) {
		try {
			put("method", method);
		} catch (JSONException e) {
			// Should never happen because "method" is a valid JSON name
			throw new RuntimeException(e);
		}
	}

	public JSONArray getParams() {
		createParamsIfNeeded();
		try {
			return getJSONArray("params");
		} catch (JSONException e) {
			// Should never happen because we created the params
			return new JSONArray();
		}
	}

	public void setParam(String param) {
		setParams(Arrays.asList(param));
	}

	public void setParams(Collection params) {
		try {
			put("params", params);
		} catch (JSONException e) {
			// Should never happen because "params" is a valid JSON name
			throw new RuntimeException(e);
		}
	}

	public void addParams(Collection params) {
		getParams().put(params);
	}

	public void addParam(String param) {
		getParams().put(param);
	}
}