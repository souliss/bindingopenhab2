/**
 * Copyright (c) 2014-2019 by the respective copyright holders.
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
public class SoulissGatewayJobPing extends Thread {

    private Logger logger = LoggerFactory.getLogger(SoulissGatewayJobPing.class);
    private String _iPAddressOnLAN;
    private short _userIndex;
    private short _nodeIndex;
    private int _pingRefreshInterval;

    private SoulissGatewayHandler gw;

    public SoulissGatewayJobPing(Bridge bridge) {
        gw = (SoulissGatewayHandler) bridge.getHandler();
        _iPAddressOnLAN = gw.IPAddressOnLAN;
        _userIndex = gw.userIndex;
        _nodeIndex = gw.nodeIndex;
        set_pingRefreshInterval(gw.pingRefreshInterval);
    }

    @Override
    public void run() {
        sendPing();
        gw.pingSent();
    }

    private void sendPing() {
        logger.debug("Sending ping packet");
        if (_iPAddressOnLAN.length() > 0) {
            SoulissCommonCommands.sendPing(SoulissBindingNetworkParameters.getDatagramSocket(), _iPAddressOnLAN,
                    _nodeIndex, _userIndex, (byte) 0, (byte) 0);
            logger.debug("Sent ping packet");
        }
    }

    public int get_pingRefreshInterval() {
        return _pingRefreshInterval;
    }

    public void set_pingRefreshInterval(int _pingRefreshInterval) {
        this._pingRefreshInterval = _pingRefreshInterval;
    }

}