package com.lxp.pro.lxpproweb.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author plutosteven
 * @since 2019-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DemoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String location;


}
