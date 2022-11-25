package sia.tacocloud.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import sia.tacocloud.models.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
