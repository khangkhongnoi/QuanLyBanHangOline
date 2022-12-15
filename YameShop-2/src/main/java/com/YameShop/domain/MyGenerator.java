package com.YameShop.domain;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;

import aj.org.objectweb.asm.Type;


public class MyGenerator implements IdentifierGenerator, Configurable {
	private String prefix;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
	    String query = "SELECT e.id FROM supplier e";
	    Stream<String> ids = session.createQuery(query, String.class).stream();
	    Long max = ids.map(o -> o.replace(prefix, "")).mapToLong(Long::parseLong).max().orElse(0L);
	    return prefix + (String.format("%04d", max + 1));
	}

    
}
