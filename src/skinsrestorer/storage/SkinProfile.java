/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 */

package skinsrestorer.storage;

import java.util.UUID;

import org.json.simple.parser.ParseException;

public class SkinProfile {

	private UUID uuid;
	private long timestamp;
	private SkinProperty playerSkinData;

	public SkinProfile(UUID uuid, SkinProperty skinData) throws ParseException {
		this.uuid = uuid;
		timestamp = System.currentTimeMillis();
		playerSkinData = skinData;
	}

	public SkinProfile(UUID uuid, SkinProperty skinData, long creationTime) throws ParseException {
		this(uuid, skinData);
		timestamp = creationTime;
	}

	public boolean isTooDamnOld() {
		return (System.currentTimeMillis() - timestamp) > (24* 60 * 60 * 1000);
	}

	public long getCreationDate() {
		return timestamp;
	}

	public UUID getUUID() {
		return uuid;
	}

	public SkinProperty getPlayerSkinProperty() {
		return playerSkinData;
	}

}
