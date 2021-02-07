package concerttours.constraints;

import concerttours.model.BandModel;
import concerttours.model.PlayerModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class RatingForBandPlayersValidator implements ConstraintValidator<RatingForBandPlayers, BandModel> {

    @Override
    public void initialize(RatingForBandPlayers ratingForBandPlayers) {
    }

    @Override
    public boolean isValid(BandModel bandModel, ConstraintValidatorContext constraintValidatorContext) {
        if (bandModel.getRatingPerPlayer() == null) {
            return true;
        }
        Set<PlayerModel> bandPlayers = Optional.ofNullable(bandModel.getPlayers()).orElse(Collections.emptySet());

        return bandModel.getRatingPerPlayer().keySet().stream()
                .noneMatch(player -> player == null || !bandPlayers.contains(player));
    }

}
