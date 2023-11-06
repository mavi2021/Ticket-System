package com.example.ticket.context;

import com.example.ticket.handler.AbstractChainHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AbstractChainContextHolder<T> implements CommandLineRunner{

    private final Map<String, List<AbstractChainHandler>> abstractChainHandlerContainer = new HashMap<>();

    public void handler(String mark, T requestParam){
        List<AbstractChainHandler> abstractChainHandlers = abstractChainHandlerContainer.get(mark);
        for (AbstractChainHandler each : abstractChainHandlers) each.handler(requestParam);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, AbstractChainHandler> abstractChainHandlerMap = ApplicationContextHolder.getBeanOfType(AbstractChainHandler.class);
        abstractChainHandlerMap.forEach((key, value)->{
            List<AbstractChainHandler> abstractChainHandlerList = abstractChainHandlerContainer.getOrDefault(value.getMark(), new ArrayList<>());
            abstractChainHandlerList.add(value);
            abstractChainHandlerContainer.put(value.getMark(), abstractChainHandlerList);
        });
    }
}
