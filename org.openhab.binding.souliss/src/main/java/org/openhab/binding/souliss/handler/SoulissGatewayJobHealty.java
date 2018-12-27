/**
 * Copyright (c) 2014-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.souliss.handler;

import org.eclipse.smarthome.core.thing.Bridge;
import org.openhab.binding.souliss.internal.protocol.SoulissBindingNetworkParameters;
import org.openhab.binding.souliss.internal.protocol.SoulissCommonCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tonino Fazio - Initial contribution
 */
public class SoulissGatewayJobHealty extends Thread {

    private Logger logger = LoggerFactory.getLogger(SoulissGatewayJobHealty.class);
    private String _iPAddressOnLAN;
    private short _userIndex;
    private short _nodeIndex;
    private int _healthRefreshInterval;

    private SoulissGatewayHandler gw;

    public SoulissGatewayJobHealty(Bridge bridge) {
        gw = (SoulissGatewayHandler) bridge.getHandler();
        _iPAddressOnLAN = gw.IPAddressOnLAN;
        _userIndex = gw.userIndex;
        _nodeIndex = gw.nodeIndex;
        set_healthRefreshInterval(gw.healthRefreshInterval);
    }

    @Override
    public void run() {
        sendHEALTHY_REQUEST();
    }

    private void sendHEALTHY_REQUEST() {
        logger.debug("Sending healthy packet");
        if (_iPAddressOnLAN.length() > 0) {
            SoulissCommonCommands.sendHEALTY_REQUESTframe(SoulissBindingNetworkParameters.getDatagramSocket(),
                    _iPAddressOnLAN, _nodeIndex, _userIndex, gw.getNodes());
            logger.debug("Sent healthy packet");
        }
    }

    public int get_healthRefreshInterval() {
        return _healthRefreshInterval;
    }

    public void set_healthRefreshInterval(int _healthRefreshInterval) {
        this._healthRefreshInterval = _healthRefreshInterval;
    }
}