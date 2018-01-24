package com.automatedbaggage.domain;

public interface GraphService<V, E> {

	Iterable<V> getNodes();

	int getOrder();

	Iterable<E> getLinks();

	int getSize();

	E getLink(V source, V target);

	boolean containsNode(V v);

	boolean containsLink(E e);
}
