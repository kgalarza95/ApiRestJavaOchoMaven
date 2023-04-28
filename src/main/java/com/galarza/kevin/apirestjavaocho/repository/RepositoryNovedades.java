/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.galarza.kevin.apirestjavaocho.repository;

import com.galarza.kevin.apirestjavaocho.dto.Response;
import java.util.Optional;

/**
 *
 * @author kgalarza
 */
public class RepositoryNovedades {

    public Optional<Response> getDataRepoData() {
        Response Rrsponse = new Response();
        return Optional.ofNullable(Rrsponse);
    }

    public Optional<Response> getDataRepoNoData() {
        Response RrsponseNull = null;
        return Optional.ofNullable(RrsponseNull);
    }
}
