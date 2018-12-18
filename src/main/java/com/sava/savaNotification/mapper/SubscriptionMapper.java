package com.sava.savaNotification.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author kateryna.savina
 */
@Mapper
public interface SubscriptionMapper {
    /**
     * Inserts a record into SUBSCRIPTION table and sets back a <b>pk</b> value of the inserted record
     *
     * @param subscription data for the subscription, can't be null
     */
    @Insert("INSERT INTO SUBSCRIPTION(ENDPOINT, AUTH, P256DH) VALUES (#{endpoint}, #{auth}, #{p256dh});")
    void create(@Param("endpoint") String endpoint, @Param("auth") String auth, @Param("p256dh") String p256dh);
}
