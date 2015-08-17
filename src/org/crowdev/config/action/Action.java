package org.crowdev.config.action;

import org.crowdev.config.Target;
import org.crowdev.config.TargetInstance;
import org.dom4j.Element;

public interface Action {

	public void act(TargetInstance targetInstance);
}
