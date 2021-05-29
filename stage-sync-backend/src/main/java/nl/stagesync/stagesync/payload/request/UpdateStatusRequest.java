package nl.stagesync.stagesync.payload.request;

import nl.stagesync.stagesync.model.EConfirmationStatus;

public class UpdateStatusRequest {

    private EConfirmationStatus confirmationStatus;

    public EConfirmationStatus getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(EConfirmationStatus confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }
}
