package com.fooddelivery.service.impl;

import com.fooddelivery.model.Cook;
import com.fooddelivery.service.CookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CookServiceImpl implements CookService {

    private HashMap<Integer, Cook> cooks = new HashMap<Integer, Cook> () {{
        put(1, new Cook (1, "Mac"));
        put(2, new Cook(2, "US100"));
    }};

    public List<Cook> getAll() {
        return new ArrayList<Cook>(cooks.values());
    }

    public Cook getById(Integer id) {
        return cooks.get(id);
    }

    public Cook save(Cook cook) {
        cooks.put(cook.getCookId(), cook);
        return cook;
    }

    public Cook deleteById(Integer id) {
        return cooks.remove(id);
    }
}