package tz.airflights.service.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tz.airflights.adapters.LocalDateTimeAdapter;
import tz.airflights.models.LoadedContent;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileLoaderImplTest {
    private FileLoaderImpl fileLoader;
    private String testFilePath;
    private Gson gson;

    @BeforeEach
    void beforeEach() {
        this.gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    @Test
    void loadFile() {
        this.testFilePath = "src\\test\\resources\\load-file.json";
        this.fileLoader = new FileLoaderImpl(testFilePath);

        LoadedContent loadedContent = fileLoader.loadFile();

        String actualContent = gson.toJson(loadedContent);

        assertEquals(getExpectedFileContent(), actualContent);
    }

    private String getExpectedFileContent() {
        return """
                  {
                    "crew": [
                      {
                        "id": 1,
                        "name": "Андрей",
                        "surname": "Андреев"
                      },
                      {
                        "id": 2,
                        "name": "Борис",
                        "surname": "Борисов"
                      },
                      {
                        "id": 3,
                        "name": "Владимир",
                        "surname": "Владимиров"
                      },
                      {
                        "id": 4,
                        "name": "Геннадий",
                        "surname": "Горький"
                      },
                      {
                        "id": 5,
                        "name": "Дмитрий",
                        "surname": "Дорогов"
                      },
                      {
                        "id": 6,
                        "name": "Евгений",
                        "surname": "Ельский"
                      }
                    ],
                    "airFlights": [
                      {
                        "aircraftType": "plane",
                        "aircraftNumber": 555,
                        "startTime": "11.01.25 | 10:15",
                        "finishTime": "11.01.25 | 15:15",
                        "startAirportName": "Москва",
                        "finishAirportName": "Сочи",
                        "crew": [
                          {
                            "id": 1,
                            "name": "Андрей",
                            "surname": "Андреев"
                          },
                          {
                            "id": 2,
                            "name": "Борис",
                            "surname": "Борисов"
                          }
                        ]
                      },
                      {
                        "aircraftType": "plane",
                        "aircraftNumber": 557,
                        "startTime": "11.01.24 | 15:15",
                        "finishTime": "11.01.24 | 16:15",
                        "startAirportName": "Москва",
                        "finishAirportName": "Сочи",
                        "crew": [
                          {
                            "id": 1,
                            "name": "Андрей",
                            "surname": "Андреев"
                          },
                          {
                            "id": 2,
                            "name": "Борис",
                            "surname": "Борисов"
                          }
                        ]
                      },
                      {
                        "aircraftType": "plane",
                        "aircraftNumber": 557,
                        "startTime": "11.02.24 | 15:15",
                        "finishTime": "12.02.24 | 00:15",
                        "startAirportName": "Москва",
                        "finishAirportName": "Сочи",
                        "crew": [
                          {
                            "id": 5,
                            "name": "Дмитрий",
                            "surname": "Дорогов"
                          },
                          {
                            "id": 6,
                            "name": "Евгений",
                            "surname": "Ельский"
                          }
                        ]
                      },
                      {
                        "aircraftType": "plane",
                        "aircraftNumber": 557,
                        "startTime": "11.03.24 | 15:15",
                        "finishTime": "11.03.24 | 21:15",
                        "startAirportName": "Москва",
                        "finishAirportName": "Сочи",
                        "crew": [
                          {
                            "id": 3,
                            "name": "Владимир",
                            "surname": "Владимиров"
                          },
                          {
                            "id": 4,
                            "name": "Геннадий",
                            "surname": "Горький"
                          }
                        ]
                      },
                      {
                        "aircraftType": "plane",
                        "aircraftNumber": 557,
                        "startTime": "12.03.24 | 15:15",
                        "finishTime": "12.03.24 | 21:15",
                        "startAirportName": "Москва",
                        "finishAirportName": "Сочи",
                        "crew": [
                          {
                            "id": 3,
                            "name": "Владимир",
                            "surname": "Владимиров"
                          },
                          {
                            "id": 4,
                            "name": "Геннадий",
                            "surname": "Горький"
                          }
                        ]
                      },
                      {
                        "aircraftType": "plane",
                        "aircraftNumber": 557,
                        "startTime": "13.03.24 | 15:15",
                        "finishTime": "13.03.24 | 21:15",
                        "startAirportName": "Москва",
                        "finishAirportName": "Сочи",
                        "crew": [
                          {
                            "id": 3,
                            "name": "Владимир",
                            "surname": "Владимиров"
                          },
                          {
                            "id": 4,
                            "name": "Геннадий",
                            "surname": "Горький"
                          }
                        ]
                      },
                      {
                        "aircraftType": "plane",
                        "aircraftNumber": 557,
                        "startTime": "14.03.24 | 15:15",
                        "finishTime": "14.03.24 | 21:15",
                        "startAirportName": "Москва",
                        "finishAirportName": "Сочи",
                        "crew": [
                          {
                            "id": 3,
                            "name": "Владимир",
                            "surname": "Владимиров"
                          },
                          {
                            "id": 4,
                            "name": "Геннадий",
                            "surname": "Горький"
                          }
                        ]
                      },
                      {
                        "aircraftType": "plane",
                        "aircraftNumber": 557,
                        "startTime": "15.03.24 | 15:15",
                        "finishTime": "15.03.24 | 21:15",
                        "startAirportName": "Москва",
                        "finishAirportName": "Сочи",
                        "crew": [
                          {
                            "id": 3,
                            "name": "Владимир",
                            "surname": "Владимиров"
                          },
                          {
                            "id": 4,
                            "name": "Геннадий",
                            "surname": "Горький"
                          }
                        ]
                      },
                      {
                        "aircraftType": "plane",
                        "aircraftNumber": 557,
                        "startTime": "16.03.24 | 15:15",
                        "finishTime": "16.03.24 | 21:15",
                        "startAirportName": "Москва",
                        "finishAirportName": "Сочи",
                        "crew": [
                          {
                            "id": 3,
                            "name": "Владимир",
                            "surname": "Владимиров"
                          },
                          {
                            "id": 4,
                            "name": "Геннадий",
                            "surname": "Горький"
                          }
                        ]
                      },
                      {
                        "aircraftType": "plane",
                        "aircraftNumber": 557,
                        "startTime": "17.03.24 | 15:15",
                        "finishTime": "17.03.24 | 21:15",
                        "startAirportName": "Москва",
                        "finishAirportName": "Сочи",
                        "crew": [
                          {
                            "id": 3,
                            "name": "Владимир",
                            "surname": "Владимиров"
                          },
                          {
                            "id": 4,
                            "name": "Геннадий",
                            "surname": "Горький"
                          }
                        ]
                      }
                    ]
                  }""";
    }
}