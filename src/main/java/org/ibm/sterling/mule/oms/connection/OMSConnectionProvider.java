package org.ibm.sterling.mule.oms.connection;

import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;


/**
 * This class (as it's name implies) provides connection instances and the funcionality to disconnect and validate those
 * connections.
 * <p>
 * All connection related parameters (values required in order to create a connection) must be
 * declared in the connection providers.
 * <p>
 * This particular example is a {@link PoolingConnectionProvider} which declares that connections resolved by this provider
 * will be pooled and reused. There are other implementations like {@link CachedConnectionProvider} which lazily creates and
 * caches connections or simply {@link ConnectionProvider} if you want a new connection each time something requires one.
 */
public class OMSConnectionProvider implements PoolingConnectionProvider<OMSConnection> {

 // private final Logger LOGGER = LoggerFactory.getLogger(OMSConnectionProvider.class);

 /**
  * A parameter that is always required to be configured.
  */
  @Parameter
  @Optional
  private String host;

 /**
  * A parameter that is not required to be configured by the user.
  */
  @Parameter
  @Optional
  private String port;

  @Parameter
  @Optional
  private String path;
 

  @Override
  public OMSConnection connect() throws ConnectionException {
    return new OMSConnection(host + port);
  }

  @Override
  public void disconnect(OMSConnection connection) {
    try {
      connection.invalidate();
    } catch (Exception e) {
     	throw e;
    //  LOGGER.error("Error while disconnecting [" + connection.getFullurlpath() + "]: " + e.getMessage(), e);
    }
  }

  @Override
  public ConnectionValidationResult validate(OMSConnection connection) {
    return ConnectionValidationResult.success();
  }
}
