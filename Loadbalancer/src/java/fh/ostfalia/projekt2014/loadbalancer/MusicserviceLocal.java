/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer;

import javax.ejb.Local;

/**
 *
 * @author Yannick
 */
@Local
public interface MusicserviceLocal {
    public void whoAmI();
}
