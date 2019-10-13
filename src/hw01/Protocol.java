package hw01;

import java.io.Serializable;

/**
 * Network communication protocol
 */
public enum Protocol implements Serializable {

    RECEIVED, READY, QUIT;
}
