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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tonino Fazio - Initial contribution
 */
public class SoulissGatewayJobSubscription extends Thread {

    private Logger logger = LoggerFactory.getLogger(SoulissGatewayJobSubscription.class);
    private String _iPAddressOnLAN;
    private short _userIndex;
    private short _nodeIndex;
    private int _subscriptionRefreshInterval;

    private SoulissGatewayHandler gw;

    public SoulissGatewayJobSubscription(Bridge bridge) {
        gw = (SoulissGatewayHandler) bridge.getHandler();
        _iPAddressOnLAN = gw.IPAddressOnLAN;
        _userIndex = gw.userIndex;
        _nodeIndex = gw.nodeIndex;
        set_subscriptionRefreshInterval(gw.subscriptionRefreshInterval);
    }

    @Override
    public void run() {
        sendSubscription();
    }

    public int get_subscriptionRefreshInterval() {
        return _subscriptionRefreshInterval;
    }

    public void set_subscriptionRefreshInterval(int _subscriptionRefreshInterval) {
        this._subscriptionRefreshInterval = _subscriptionRefreshInterval;
    }

    private void sendSubscription() {
        gw.sendSubscription();
    }
}