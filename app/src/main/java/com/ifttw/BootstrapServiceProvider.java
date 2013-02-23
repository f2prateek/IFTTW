
package com.ifttw;

import android.accounts.AccountsException;

import com.ifttw.authenticator.ApiKeyProvider;
import com.ifttw.core.BootstrapService;
import com.ifttw.core.UserAgentProvider;
import com.google.inject.Inject;

import java.io.IOException;

/**
 * Provider for a {@link com.ifttw.core.BootstrapService} instance
 */
public class BootstrapServiceProvider {

    @Inject private ApiKeyProvider keyProvider;
    @Inject private UserAgentProvider userAgentProvider;

    /**
     * Get service for configured key provider
     * <p>
     * This method gets an auth key and so it blocks and shouldn't be called on the main thread.
     *
     * @return bootstrap service
     * @throws IOException
     * @throws AccountsException
     */
    public BootstrapService getService() throws IOException, AccountsException {
        return new BootstrapService(keyProvider.getAuthKey(), userAgentProvider);
    }
}
