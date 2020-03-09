package org.openhab.binding.souliss.handler;

/**
 * Result callback interface.
 */
public interface TypicalCommonMethods {
    void setRawState(byte _rawState);

    byte getRawState();

    byte getExpectedRawState(byte bCommand);
}
