package net.security.youtube.suleimanov.rest;

import net.security.youtube.suleimanov.model.Developer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestController {
    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Ivan", "Ivanov"),
            new Developer(2L, "Sergey", "Sergeev"),
            new Developer(3L, "Petr", "Petrov")
    ).collect(Collectors.toList());

    // вверху собрали девелопером в лист

    @GetMapping
    public List<Developer> getAll(){
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id){
        return DEVELOPERS.stream().filter(developer -> { return Objects.equals(developer.getId(), id);
                })
                .findFirst()
                    .orElse(null);
    }

    @PostMapping
    public Developer create(@RequestBody Developer developer) { // RequestBody - принимает значение из тела запроса
        this.DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long id){
        this.DEVELOPERS.removeIf(developer -> {
            return Objects.equals(developer.getId(), id);
        });
    }
}
