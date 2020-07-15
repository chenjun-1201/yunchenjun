package com.itcast.service;

import com.itcast.domain.Syslog;

import java.util.List;

public interface ISyslogService {
    public void save(Syslog syslog);

    List<Syslog> findall();
}
