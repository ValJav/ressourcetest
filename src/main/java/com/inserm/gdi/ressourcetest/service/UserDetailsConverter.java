package com.inserm.gdi.ressourcetest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inserm.gdi.ressourcetest.model.CustomUserDetails;

public class UserDetailsConverter {

    private UserDetailsConverter() {
        // Private constructor to hide implicit public one
    }

    public static CustomUserDetails convert(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode dataNode = rootNode.path("data");
        String username = dataNode.path("username").asText();
        String password = "**********";
        boolean accountNonExpired = dataNode.path("accountNonExpired").asBoolean();
        boolean accountNonLocked = dataNode.path("accountNonLocked").asBoolean();
        boolean credentialsNonExpired = dataNode.path("credentialsNonExpired").asBoolean();
        boolean enabled = dataNode.path("enabled").asBoolean();

        JsonNode authoritiesNode = dataNode.path("authorities");
        List<SimpleGrantedAuthority> listGrantedAuthority = new ArrayList<>();

        for (JsonNode authorityNode : authoritiesNode) {
            String authority = authorityNode.get("authority").asText();
            listGrantedAuthority.add(new SimpleGrantedAuthority(authority));
        }
        return new CustomUserDetails(username, password, listGrantedAuthority, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
    }
}