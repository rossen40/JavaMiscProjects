package mainApp.model;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link Version} This class holds version information
 *
 * @author Konstantin_Polihronov - Initial contribution
 */
public class Version {
	private static Logger logger = LoggerFactory.getLogger(Version.class);

	private Byte version;
	private Byte revision;
	private Byte build;
	private Date buildTime;

	public Version(Byte version, Byte revision) {
		this(version, revision, null);
	}

	public Version(Byte version, Byte revision, Byte build) {
		this(version, revision, build, null);
	}

	public Version(Byte version, Byte revision, Byte build, Date buildTime) {
		this.version = version;
		this.revision = revision;
		this.build = build;
		this.buildTime = buildTime;
		logger.debug(this.toString());
	}

	public Byte getVersion() {
		return version;
	}

	public void setVersion(Byte version) {
		this.version = version;
	}

	public Byte getRevision() {
		return revision;
	}

	public void setRevision(Byte revision) {
		this.revision = revision;
	}

	public Byte getBuild() {
		return build;
	}

	public void setBuild(Byte build) {
		this.build = build;
	}

	public Date getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Version: ");
		sb.append(version);
		sb.append(".");
		sb.append(revision);
		if (build != null) {
			sb.append(".");
			sb.append(build);
		}
		if (buildTime != null) {
			sb.append("/");
			sb.append(buildTime);
		}
		return sb.toString();
	}

}
