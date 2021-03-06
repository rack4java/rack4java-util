package org.rack4java.context;

import java.util.Iterator;
import java.util.Map;

import org.rack4java.Context;
import org.rack4java.utils.CascadeIterator;

public class FallbackContext<T> implements Context<T> {
	private Context<T>[] list;
	
	public FallbackContext(Context<T>... list) {
		this.list = list;
	}

	@Override public T get(String key) {
		for (Context<? extends T> context : list) {
			T ret = context.get(key);
			if (null != ret) return ret;
		}
		return null;
	}

	@Override public Iterator<Map.Entry<String,T>> iterator() {
		return new CascadeIterator<Map.Entry<String,T>>(list);
	}

	@Override public Context<T> with(String key, Object value) {
		list[0].with(key, value);
		return this;
	}

	@Override public Object remove(String key) {
		return list[0].remove(key);
	}

	@Override public Object getObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}
}
