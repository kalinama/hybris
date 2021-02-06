package concerttours.attributehandlers;

import concerttours.model.BandModel;
import concerttours.model.PlayerModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BandPlayersInPopularityOrderAttributeHandler extends AbstractDynamicAttributeHandler<List<String>, BandModel> {
    @Override
    public List<String> get(final BandModel model) {
        Map<PlayerModel, Long> ratingPerPlayer = Optional.ofNullable(model.getRatingPerPlayer())
                .orElse(Collections.emptyMap());

        return ratingPerPlayer.entrySet().stream()
                .sorted(Collections.reverseOrder(Comparator.comparingLong(Map.Entry::getValue)))
                .map(entry -> entry.getKey().getAlias())
                .collect(Collectors.toList());
    }
}