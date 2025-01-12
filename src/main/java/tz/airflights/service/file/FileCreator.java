package tz.airflights.service.file;

import tz.airflights.models.stat.Stat;

import java.util.List;

public interface FileCreator {
    void createFile(List<Stat> stats);
}
