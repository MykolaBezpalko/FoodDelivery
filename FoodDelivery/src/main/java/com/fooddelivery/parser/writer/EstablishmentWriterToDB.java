package com.fooddelivery.parser.writer;

import com.fooddelivery.model.Establishment;
import com.fooddelivery.service.DishService;
import com.fooddelivery.service.EstablishmentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

/**
 * Class that used in {@link com.fooddelivery.parser.Parser} to write all establishments to database
 */
@Component
public class EstablishmentWriterToDB {

    @Resource
    private EstablishmentService establishmentService;
    @Resource
    private DishService dishService;

    /**
     * Out establishments to database
     *
     * @param menus {@link Collection} of {@link Establishment establishments}
     */
    public void outToDB(Collection<Establishment> menus) {
        menus.forEach(establishment -> {
            establishmentService.create(establishment);
            establishment.getDishes().forEach(dishService::create);
        });
    }

    /**
     * Out establishments to database
     *
     * @param menus {@link Map} where value is {@link Establishment}
     */
    public void outToDB(Map<String, Establishment> menus) {
        this.outToDB(menus.values());
    }

}
