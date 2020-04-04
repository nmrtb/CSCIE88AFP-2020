package cscie88a.streams;

import cscie88a.basics4.ActionResult;

public class AdoptionService {

    public ActionResult tryToAdopt(IAdoptable someoneToAdopt) {
        if (someoneToAdopt.isAdoptable()) {
            return ActionResult.SUCCESS;
        } else
            return ActionResult.FAILURE;
    }

}
