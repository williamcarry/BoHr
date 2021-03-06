/**
 * Copyright (c) 2019 The Bohr Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.bohr.config;

import java.util.Collections;
import java.util.Map;

import org.bohr.Network;
import org.bohr.core.Fork;

public class TestnetConfig extends AbstractConfig {

    public TestnetConfig(String dataDir) {
        super(dataDir, Network.TESTNET, Constants.TESTNET_VERSION);

        this.forkUniformDistributionEnabled = true;
        this.forkVirtualMachineEnabled = true;
        this.forkVotingPrecompiledUpgradeEnabled = true;
    }

    @Override
    public Map<Long, byte[]> checkpoints() {
        return Collections.emptyMap();
    }

    @Override
    public Map<Fork, Long> manuallyActivatedForks() {
        return Collections.emptyMap();
    }

    /**
     * Testnet maxes out at 10 validators to stop dead validators from breaking
     * concensus
     * 
     * @param number
     * @return
     */
    @Override
    public int getNumberOfValidators(long number) {
        return Math.min(10, super.getNumberOfValidators(number));
    }
}
