package concerttours.attributehandlers;

import concerttours.model.BandModel;
import concerttours.model.PlayerModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BandPlayersInPopularityOrderAttributeHandler extends AbstractDynamicAttributeHandler<List<String>, BandModel> {
    @Override
    public List<String> get(final BandModel model) {
        Map<PlayerModel, Long> ratingPerPlayer = model.getRatingPerPlayer();
        if (ratingPerPlayer == null) {
            return null;
        }
        return ratingPerPlayer.entrySet().stream()
                .sorted(Collections.reverseOrder(Comparator.comparingLong(Map.Entry::getValue)))
                .map(entry -> entry.getKey().getAlias())
                .collect(Collectors.toList());
    }

    @Override
    public void set(BandModel model, List<String> playersAliases) {
        throw new UnsupportedOperationException();
    }
}