package com.itcast.service.impl;

import com.itcast.dao.ISyslogDao;
import com.itcast.domain.Syslog;
import com.itcast.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SyslogServiceImpl implements ISyslogService {
    @Autowired
    private ISyslogDao iSyslogDao;
    @Override
    public void save(Syslog syslog) {
        iSyslogDao.addsyslog(syslog);

    }

    @Override
    public List<Syslog> findall() {
        List<Syslog> list = iSyslogDao.findall();
        return list;
    }
}
