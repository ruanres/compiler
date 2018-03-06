package core;

public class IfThen extends ScopedEntity{
    private Register sentinelRegister;

    public IfThen(Register sentinel) {
        super("IfThen");
        this.sentinelRegister = sentinel;
    }

    public Register getSentinel() {
        return this.sentinelRegister;
    }
}
