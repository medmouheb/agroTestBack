package com.agrotech.api.Repository;

import java.util.List;
import java.util.Map;
public interface TaskRepositoryCustom {
    List<Map> countTasksByCreatedAtDate();
    List<Map> countTasksByOwner();

    List<Map> countTasksByCreatedAtDateAndOwner(String owner);
    List<Map> countTasksByActur(String owner);
    List<Map> countTasksByActurAndList(String owner);

}
